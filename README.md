# IP Cache
The same API implemented in multiple languages / frameworks to compare LoCs, complexity and performance.

## Side by side
```bash
$ scc echoIPC/ djangoIPC/core --exclude-dir migrations -i go,py
───────────────────────────────────────────────────────────────────────────────
Language                 Files     Lines   Blanks  Comments     Code Complexity
───────────────────────────────────────────────────────────────────────────────
Go                          10       310       60         0      250         30
Python                       8       134       35         0       99          1
───────────────────────────────────────────────────────────────────────────────
```

## Python Django
Only code under *core* is included (migrations, *manage.py* and most code under *app* was auto-generated):
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

## Go Echo
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