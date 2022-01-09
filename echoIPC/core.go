package echoIPC

import (
	"gorm.io/datatypes"
	"time"
)

type Ip struct {
	Ip       string `gorm:"primarykey"`
	Payload  datatypes.JSON
	Modified time.Time `gorm:"autoUpdateTime"`
}

type CoreSrv interface {
	GetCachedIps(ii *[]Ip) error
	GetIpPayload(i *Ip) error
}
