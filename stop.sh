#!/bin/sh
pid = $(ps aux | grep businessmanager | grep -v grep | awk '{print $2}')
echo "Process : $pid"