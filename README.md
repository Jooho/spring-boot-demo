# spring-boot-demo

## Build
```
oc new-app --template=openjdk18-web-basic-s2i -p SOURCE_REPOSITORY_URL=https://github.com/Jooho/spring-boot-demo -p CONTEXT_DIR=""
oc patch bc/openjdk-app  -p '{"spec":{"strategy": {"sourceStrategy": {"incremental": true}}}}'

### git commit 
oc start-build openjdk-app
docker pull $(oc get is  openjdk-app -o jsonpath='{.status.dockerImageRepository}')
docker tag $(oc get is openjdk-app -o jsonpath='{.status.dockerImageRepository}') docker.io/ljhiyh/auth
docker push docker.io/ljhiyh/auth
```
