# Spring Cloud Config 3.0 Demo 

###Pre-req
* Config server instance named "config-server". 
```bash
cf create-service p.config-server standard config-server -c '{"git": { "uri": "https://github.com/spring-cloud-workspace/confgurations" } }'
```

### Sample workflow
* Step 1 - Look up current message
    * ``$ http https://scs-demo.apps.pcfone.io/message``
* Step 2 - Update config repo with new message
    * `$ git commit -am "new message"`
    * `$ git push`
* Step 3 - Update config-server mirror
    * ``$ cf update-service config-server -c '{"update-git-repos": true }'``
* Step 4 - Refresh spring beans
    * `$ http POST https://scs-demo.apps.pcfone.io/actuator/refresh`
* Step 5 - Get new message
    * `$ http https://scs-demo.apps.pcfone.io/message`

### Sample workflow with CredHub secret store
* Step 1 - Add Secret
```bash
http PUT https://config-server-xxx-xxx-xxx-xxx-xxx.apps.pcfone.io/secrets/scs-demo-client/development/master/secretMessage Authorization:"$(cf oauth-token)" secretMessage=Taco
```

* Step 2: Refresh actuator endpoint
```bash
http POST https://scs-demo.apps.pcfone.io/actuator/refresh
```

* Step 3: Access
```bash
http https://scs-demo.apps.pcfone.io/secretMessage
```