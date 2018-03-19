# performance-test-tool
common performance test tool

```
Usage: performance test tool [options]
  Options:

  * --test, -t
      test case class, such as com.test.performance.demo.DemoTestCaseImpl

    -program
    
    -testname
    
    -runid
      run id for this test, default is date

    -thread
      
    -duration
      keep how much time in second for test
      Default: 10
      
    -tps

    -reportinterval
      interval in seconds to report progress
      Default: 5

    --record, -r
      record test result class, such as 
      com.test.performance.result.impl.DefaultCollectMethodImpl 
      Default: com.test.performance.result.impl.DefaultCollectMethodImpl
      
    -D
      dynamic parameters
      Syntax: -Dkey=value
      Default: {}
      
    -help

```

## Usage: help
```
compile exec:java -Dexec.mainClass="com.test.performance.PerformanceTool" -Dexec.args="-help"
```

## Usage: test with 1 thread and 1 TPS

```
compile exec:java -Dexec.mainClass="com.test.performance.PerformanceTool" -Dexec.args="-t com.test.performance.demo.DemoTestCaseImpl -duration 20"
```

## Usage: no limit TPS, just use N thread to loop execute

```
compile exec:java -Dexec.mainClass="com.test.performance.PerformanceTool" -Dexec.args="-t com.test.performance.demo.DemoTestCaseImpl -duration 20 -thread 5"
```

## Usage: try to reach limit TPS by unlimited thread
```
compile exec:java -Dexec.mainClass="com.test.performance.PerformanceTool" -Dexec.args="-t com.test.performance.demo.DemoTestCaseImpl -duration 20 -tps 30"
```

## Usage: try to reach limit TPS by N thread
```
compile exec:java -Dexec.mainClass="com.test.performance.PerformanceTool" -Dexec.args="-t com.test.performance.demo.DemoTestCaseImpl -duration 20 -thread 5 -tps 30"
```

# example:
```
 compile exec:java -Dexec.mainClass="com.test.performance.PerformanceTool" -Dexec.args="-program MyProgramName -testname TestWebService -runid ThisRunId -t com.test.mytestcaseimpl -r com.test.performance.result.impl.InfluxdbCollectMethodImpl  -duration 60 -tps 80 -Ddsaip=10.224.2.100 -DinfluxdbUrl=http://10.224.2.101:8086 -DinfluxdbDatabase=MetricDatabaseName -DinfluxdbUsername=admin -DinfluxdbPassword=admin "
 ```


