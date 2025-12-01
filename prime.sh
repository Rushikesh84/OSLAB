#!/bin/bash

# Script to check if a number is prime

read -p "Enter a number: " num

if [ $num -lt 2 ]; then
    echo "$num is not a prime number"
    exit 0
fi

if [ $num -eq 2 ]; then
    echo "$num is a prime number"
    exit 0
fi

if [ $((num % 2)) -eq 0 ]; then
    echo "$num is not a prime number"
    exit 0
fi

i=3
while [ $((i * i)) -le $num ]; do
    if [ $((num % i)) -eq 0 ]; then
        echo "$num is not a prime number"
        exit 0
    fi
    i=$((i + 2))
done

echo "$num is a prime number"
