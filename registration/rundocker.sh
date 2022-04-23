#!/bin/bash
docker build . --tag register_user
docker stop `docker ps |grep register_user | awk '{print $1}'`
docker run -p 8080:8080 -d register_user

