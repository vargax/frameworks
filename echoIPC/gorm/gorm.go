package gorm

import (
	"github.com/vargax/frameworks/echoIPC"
	"gorm.io/driver/sqlite"
	"gorm.io/gorm"
	"gorm.io/gorm/logger"
	"log"
)

type DbMdl struct {
	db *gorm.DB
}

func New() *DbMdl {
	db, err := gorm.Open(sqlite.Open("db.sqlite3"), &gorm.Config{
		Logger: logger.Default.LogMode(logger.Silent),
	})
	if err != nil {
		panic(err)
	}

	err = db.AutoMigrate(&echoIPC.Ip{})
	if err != nil {
		panic(err)
	}

	return &DbMdl{db: db}
}

func (dbm *DbMdl) InsertIp(i *echoIPC.Ip) error {
	r := dbm.db.Create(&i)
	return r.Error
}

func (dbm DbMdl) SelectIp(i *echoIPC.Ip) error {
	r := dbm.db.First(&i)
	return r.Error
}

func (dbm *DbMdl) SelectAllIps(ii *[]echoIPC.Ip) error {
	r := dbm.db.Find(ii)
	log.Printf("%v IPs found in the cache", len(*ii))
	return r.Error
}
