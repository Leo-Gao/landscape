# landscape

using two build tool  :  maven , ant 

1. build tool : maven --   command: mvn package
   execution commancd : java -classpath start.Simulation small.dat
   (parameters :  the landscape description , a ASCII file)
   Note: run executable jar file , please work in target directory

2. build tool:  ant  -- command : ant runSimulation
   Note : executing 'ant' need to work in 'ant' directory

 if want change parameters, check and modify property.properties.

entrance class :  start.Simulation.java

output directory of ppm file:  ./output

output of average data : command sceen 
