#!/bin/bash

g++ --version

ECHOUE=false

cd ..
ls
cd src
cd cpp

echo "Nous sommes ici : $(pwd)"

ls *.cpp >> fich.txt

echo "LISTE de fichiers à compiler : "
echo ""
cat fich.txt | xargs -I % sh -c 'NOM=%; echo "\e[93m $NOM \e[0m" ' # couleur jaune clair
echo ""

entreefich="fich.txt"

while IFS= read -r line
do
   echo -e "Va compiler : \e[92m  $line \e[0m" # couleur vert clair
   echo ""
   g++ -Wall -std=c++14 -fdiagnostics-color=always -c  "$line"  >> log.txt
   if [ $? -ne 0 ]
   then
    ECHOUE=true
  fi

   echo "$(cat log.txt)"
   echo "Fin compilation de $line"
   echo ""
done < "$entreefich"

echo "Fin compilation"
echo ""

echo "Edition de liens  "
g++ -o exemple $(ls *.o | tr '\n' ' ')

if [ $? -ne 0 ]
then
  exit 1
fi

if [ "$ECHOUE" = true ]
then
   exit 1
 fi
