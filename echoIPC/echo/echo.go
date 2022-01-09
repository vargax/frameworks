package echo

import (
	"github.com/labstack/echo/v4"
	"github.com/vargax/frameworks/echoIPC"
	"github.com/vargax/frameworks/echoIPC/echo/validator"

	"os"
)

type WebMdl struct {
	echo *echo.Echo
	ss   *echoIPC.Services
}

func New(services *echoIPC.Services) *WebMdl {
	wm := WebMdl{
		echo: echo.New(),
		ss:   services,
	}

	wm.echo.Validator = validator.New()

	wm.routes()
	return &wm
}

func (wm *WebMdl) Start() {
	err := wm.echo.Start(os.Getenv(echoIPC.EchoPort))
	if err != nil {
		panic(err)
	}
}
