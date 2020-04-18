#!/bin/bash

ab -c 256 -n 4192 -T 'application/json' -p login.json http://localhost:8080/login