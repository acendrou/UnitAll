#!/bin/bash

function compile () {
  
  root_directory="$(pwd)"
  
  cd $source_folder
  full_source_folder="$(pwd)"
  echo -n "working directory:"; pwd

  # compile from source file
  javac -d ./temp *.java
  
  if [ $? -ne 0 ]
  then
  echo "compilation error"
  exit -1
  fi
  
  mkdir ./temp/META-INF/
  cp MANIFEST.MF ./temp/META-INF/MANIFEST.MF
  
  cd ./temp
  

  # create jar file
  jar cmvf ./META-INF/MANIFEST.MF "$name.jar" *.class

  if [ $? -ne 0 ]
  then
  echo "jar creation error"
  exit -1
  fi
  
  cd $root_directory
  mkdir $destination_folder
  cd $destination_folder
 
  cp "$full_source_folder/temp/$name.jar" $name.jar
  
	
  echo -e "\e[33m done" # yellow color
}


# process command line arguments
while getopts s:d:n: flag
do
    case "$flag" in
        s) source_folder="$OPTARG";;
        d) destination_folder="$OPTARG";;
        n) name="$OPTARG";;
        \?) # Invalid option
         echo "Error: Invalid option"
         exit;;
    esac
done


# check if arguments are correct
if [ -z $source_folder ] || [ -z $destination_folder ] || [ -z $name ]
then
  echo "invalid argument: please retype"
  exit -1
fi

echo -e "\e[33m starting" # yellow color
echo "source folder: $source_folder";
echo "destination folder: $destination_folder";
echo "name: $name";

# compile and generate jar file
compile;
