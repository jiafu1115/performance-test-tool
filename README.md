Usage: performance test tool [options]
  Options:
    --record, -r
      record test result class, such as 
      com.test.performance.result.DefaultCollectMethodImpl 
      Default: com.test.performance.result.DefaultCollectMethodImpl
  * --test, -t
      test case class, such as com.test.performance.demo.DemoTestCaseImpl
    -D
      dynamic parameters
      Syntax: -Dkey=value
      Default: {}
    -duration
      keep how much time in second for test
      Default: 10
    -help

    -program

    -reportinterval
      interval in seconds to report progress
      Default: 5
    -runid
      run id for this test, default is date
      Default: Sun Mar 18 18:59:13 CST 2018
    -thread

      Default: -1
    -tps

      Default: -1

