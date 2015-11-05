# landscape

using two build tools  :  maven , ant 

1. build tool : maven --   command: mvn package
   execution commancd : java -classpath start.Simulation small.dat
   (parameters :  the landscape description , a ASCII file)
   Note: run executable jar file , please work in target directory

2. build tool:  ant  -- command : ant runSimulation
   Note : work in 'ant' directory when executing 'ant' need to 

 parameters can be changed by modifying property.properties.

entrance class :  start.Simulation.java

output directory of ppm files:  ./output

running output of average population densities : command sceen 
