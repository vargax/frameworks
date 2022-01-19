# IP Cache

The same API implemented in multiple languages / frameworks to compare LoCs, complexity and performance.

## Side by side

Sorted by LoC:

```bash
$ scc djangoIPC/ echoIPC/ springIPC/ -i py,go,java -s code 
───────────────────────────────────────────────────────────────────────────────
Language                 Files     Lines   Blanks  Comments     Code Complexity
───────────────────────────────────────────────────────────────────────────────
Python                      17       406       77        43      286          6
Go                          10       310       60         0      250         30
Java                         6       200       41         0      159          4
───────────────────────────────────────────────────────────────────────────────
```

Excluding Django generated code:

```bash
$ scc djangoIPC/core echoIPC/ springIPC/ --exclude-dir migrations -i py,go,java -s code
───────────────────────────────────────────────────────────────────────────────
Language                 Files     Lines   Blanks  Comments     Code Complexity
───────────────────────────────────────────────────────────────────────────────
Go                          10       310       60         0      250         30
Java                         6       200       41         0      159          4
Python                       8       134       35         0       99          1
───────────────────────────────────────────────────────────────────────────────
Total                       24       644      136         0      508         35
───────────────────────────────────────────────────────────────────────────────
```

## Python Django

Only *.py* files under *core* (excluding auto-generated code like *migrations*, *manage.py* and code under *app*):

```bash
$ scc djangoIPC/core --exclude-dir migrations
───────────────────────────────────────────────────────────────────────────────
Language                 Files     Lines   Blanks  Comments     Code Complexity
───────────────────────────────────────────────────────────────────────────────
Python                       8       134       35         0       99          1
───────────────────────────────────────────────────────────────────────────────
Estimated Cost to Develop (organic) $2,382
Estimated Schedule Effort (organic) 1.385730 months
Estimated People Required (organic) 0.152739
───────────────────────────────────────────────────────────────────────────────
Processed 3804 bytes, 0.004 megabytes (SI)
───────────────────────────────────────────────────────────────────────────────
```

Whole project:

```bash
$ scc djangoIPC/app djangoIPC/core djangoIPC/requirements.in djangoIPC/README.md -s Code
───────────────────────────────────────────────────────────────────────────────
Language                 Files     Lines   Blanks  Comments     Code Complexity
───────────────────────────────────────────────────────────────────────────────
Python                      16       384       77        42      265          4
Markdown                     1        69       13         0       56          0
Autoconf                     1         2        0         0        2          0
───────────────────────────────────────────────────────────────────────────────
Total                       18       455       90        42      323          4
───────────────────────────────────────────────────────────────────────────────
Estimated Cost to Develop (organic) $8,246
Estimated Schedule Effort (organic) 2.221223 months
Estimated People Required (organic) 0.329824
───────────────────────────────────────────────────────────────────────────────
Processed 12821 bytes, 0.013 megabytes (SI)
───────────────────────────────────────────────────────────────────────────────
```

## Java Spring

Only *.java* files:

```bash
$ scc springIPC/ -i java                                                       
Language                 Files     Lines   Blanks  Comments     Code Complexity
───────────────────────────────────────────────────────────────────────────────
Java                         6       200       41         0      159          4
───────────────────────────────────────────────────────────────────────────────
Total                        6       200       41         0      159          4
───────────────────────────────────────────────────────────────────────────────
Estimated Cost to Develop (organic) $3,917
Estimated Schedule Effort (organic) 1.674086 months
Estimated People Required (organic) 0.207922
───────────────────────────────────────────────────────────────────────────────
```

Whole project:

```bash
$ scc springIPC/src springIPC/pom.xml -s code
───────────────────────────────────────────────────────────────────────────────
Language                 Files     Lines   Blanks  Comments     Code Complexity
───────────────────────────────────────────────────────────────────────────────
Java                         6       200       41         0      159          4
XML                          1        69        2         0       67          0
Properties File              1         3        0         1        2          0
───────────────────────────────────────────────────────────────────────────────
Total                        8       272       43         1      228          4
───────────────────────────────────────────────────────────────────────────────
Estimated Cost to Develop (organic) $5,720
Estimated Schedule Effort (organic) 1.933020 months
Estimated People Required (organic) 0.262910
───────────────────────────────────────────────────────────────────────────────
Processed 8586 bytes, 0.009 megabytes (SI)
───────────────────────────────────────────────────────────────────────────────
```

## Go Echo

Only *.go* files:

```bash
$ scc echoIPC/ -i go
───────────────────────────────────────────────────────────────────────────────
Language                 Files     Lines   Blanks  Comments     Code Complexity
───────────────────────────────────────────────────────────────────────────────
Go                          10       310       60         0      250         30
───────────────────────────────────────────────────────────────────────────────
Estimated Cost to Develop (organic) $6,301
Estimated Schedule Effort (organic) 2.005388 months
Estimated People Required (organic) 0.279158
───────────────────────────────────────────────────────────────────────────────
Processed 5118 bytes, 0.005 megabytes (SI)
───────────────────────────────────────────────────────────────────────────────
```

Whole project:

```bash
$ scc echoIPC/ --exclude-dir .idea -s code
───────────────────────────────────────────────────────────────────────────────
Language                 Files     Lines   Blanks  Comments     Code Complexity
───────────────────────────────────────────────────────────────────────────────
Go                          10       310       60         0      250         30
Markdown                     1        51       16         0       35          0
───────────────────────────────────────────────────────────────────────────────
Total                       11       361       76         0      285         30
───────────────────────────────────────────────────────────────────────────────
Estimated Cost to Develop (organic) $7,230
Estimated Schedule Effort (organic) 2.113019 months
Estimated People Required (organic) 0.304015
───────────────────────────────────────────────────────────────────────────────
Processed 6817 bytes, 0.007 megabytes (SI)
───────────────────────────────────────────────────────────────────────────────
```