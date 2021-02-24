.PHONY: repl

prod:
	clj -m cljs.main --optimizations advanced -c reconnect.core

repl:
	clj --main cljs.main --compile reconnect.core --repl
