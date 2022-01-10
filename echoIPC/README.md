# Echo IP Cache

This sample *Echo* project was designed following the guidelines shared by Ashley McManara on
[*Go best practices*](https://www.youtube.com/watch?v=MzTcsI6tn-0).

The entry point is [main.go](cmd/main.go) and config parameters are passed as env variables ([env.go](env.go) and
[.env](.env)).

## Interfaces

*Go* is a strongly statically typed language and its compiler doesn't like import loops. This means there **can be no**
cross dependencies between *packages*.

Application logic is implemented on *services*, while dependency-specific logic is on *modules*. Both *services* and
*modules* are *packages* for the compiler.

*Services* (app logic) and *modules* (dependencies) are glue together through *interfaces*:

- [db.go](db.go): *Interface* for the *db module*.
- [core.go](core.go): Contains business objects (*struct*) and the *interface* for the *IP Cache service*.
- [web.go](web.go): *Interface* for the *web module*.

### Services

Services are under the [*services package*](services). There is only one service: [core.go](services/core.go)

### Modules

- [*GORM*](https://gorm.io/) is used as ORM for the *db module* and its implementation is under the
  [*gorm package*](gorm)
- [*echo*](https://echo.labstack.com/) is the framework used for the *web module* and its implementation is under the
  [*echo package*](echo)

# Start from scratch

Initialize a module using the repository URL:

```bash
go mod init github.com/vargax/frameworks/echoIPC
```

## Managing dependencies
To add a package / dependency:
```bash
go get -u gorm.io/gorm
```

To update all dependencies:
```bash
go get -u && go mod tidy
```