#!/bin/bash

# Palindrome checker script

read -p "Enter a string: " str

# Remove spaces and convert to lowercase
cleaned=$(echo "$str" | tr -d ' ' | tr '[:upper:]' '[:lower:]')

# Reverse the string
reversed=$(echo "$cleaned" | rev)

# Compare original and reversed
if [ "$cleaned" = "$reversed" ]; then
    echo "'$str' is a palindrome"
else
    echo "'$str' is not a palindrome"
fi