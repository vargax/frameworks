package echoIPC

import "gorm.io/gorm"

type DbMdl interface {
	InsertIp(i *Ip) error
	SelectIp(i *Ip) error
	SelectAllIps(ii *[]Ip) error
}

var (
	RecordNotFound = gorm.ErrRecordNotFound
)
