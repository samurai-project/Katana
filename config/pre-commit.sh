#!/bin/sh

# Or You can use it by running this command from the project root:
# `ln -s ../../config/pre-commit.sh .git/hooks/pre-commit`

echo "Running detekt."

set -e

./gradlew detekt --daemon

status=$?

if [ "$status" = 0 ] ; then
    echo "Static analysis found no problems."
    exit 0
else
    echo 1>&2 "Static analysis found violations it could not fix."
    exit 1
fi