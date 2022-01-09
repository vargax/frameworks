package services

import (
	"errors"
	"fmt"
	"github.com/vargax/frameworks/echoIPC"
	"io/ioutil"
	"log"
	"net/http"
	"os"
)

type CoreSrv struct {
	ipApiUrl string
	dbm      echoIPC.DbMdl
}

func New(dbMdl echoIPC.DbMdl) *CoreSrv {
	return &CoreSrv{
		ipApiUrl: os.Getenv(echoIPC.IpApiUrl),
		dbm:      dbMdl,
	}
}

func (cs CoreSrv) GetIpPayload(i *echoIPC.Ip) error {
	err := cs.dbm.SelectIp(i)
	if errors.Is(err, echoIPC.RecordNotFound) {
		log.Printf("%s wasn't found locally...", i.Ip)

		err := cs.queryIpApi(i)
		if err != nil {
			return err
		}

		err = cs.dbm.InsertIp(i)
		if err != nil {
			return err
		}
	}
	return nil
}

func (cs CoreSrv) GetCachedIps(ii *[]echoIPC.Ip) error {
	return cs.dbm.SelectAllIps(ii)
}

func (cs CoreSrv) queryIpApi(i *echoIPC.Ip) error {
	log.Printf("Querying IP-API for %s", i.Ip)
	q := fmt.Sprintf("%s%s", cs.ipApiUrl, i.Ip)

	r, err := http.Get(q)
	if err != nil {
		return err
	}

	p, err := ioutil.ReadAll(r.Body)
	if err != nil {
		return err
	}

	i.Payload = p
	return nil
}
