package main

import (
	"github.com/joho/godotenv"
	"github.com/vargax/frameworks/echoIPC"
	"github.com/vargax/frameworks/echoIPC/echo"
	"github.com/vargax/frameworks/echoIPC/gorm"
	"github.com/vargax/frameworks/echoIPC/services"
)

var (
	ss echoIPC.Services
	mm modules
)

type modules struct {
	storage echoIPC.DbMdl
	web     echoIPC.WebMdl
}

func main() {
	if err := godotenv.Load(".env"); err != nil {
		panic(err)
	}

	mm.storage = gorm.New()

	ss = echoIPC.Services{
		CoreSrv: services.New(mm.storage),
	}

	mm.web = echo.New(&ss)
	mm.web.Start()
}
