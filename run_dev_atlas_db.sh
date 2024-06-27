#!/bin/bash

export $(xargs <.env)

./gradlew bootRunDev