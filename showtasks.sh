#!/usr/bin/env bash
browser(){
open "http://localhost:8080/crud/v1/task/getTasks"
}
fail(){
   echo "There were errors"
}

if ./runcrud.sh; then
  browser
  else
   fail
fi