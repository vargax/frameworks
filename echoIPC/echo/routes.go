package echo

import (
	"github.com/labstack/echo/v4"
	"github.com/vargax/frameworks/echoIPC"
	"log"
	"net/http"
)

func (wm *WebMdl) routes() {
	wm.echo.GET("/:ip", wm.getIp)
	wm.echo.GET("/", wm.getAllIps)
}

func (wm *WebMdl) getAllIps(c echo.Context) error {
	log.Print("All IPs requested...")
	var ii []echoIPC.Ip

	err := wm.ss.CoreSrv.GetCachedIps(&ii)
	if err != nil {
		return err
	}

	return c.JSON(http.StatusOK, ii)
}

func (wm *WebMdl) getIp(c echo.Context) error {
	i := echoIPC.Ip{
		Ip: c.Param("ip"),
	}
	log.Printf("%s requested...", i.Ip)

	if err := c.Validate(i); err != nil {
		return echo.NewHTTPError(http.StatusBadRequest, err.Error())
	}

	err := wm.ss.CoreSrv.GetIpPayload(&i)
	if err != nil {
		return err
	}

	return c.JSON(http.StatusOK, i.Payload)
}
