package zoo

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object TestApp {
  def main(args: Array[String]) {
    val driverPort = 7777
    val driverHost = "localhost"
    val conf = new SparkConf(false)
      .setMaster("local[*]")
      .setAppName("Test Spark App")
      .set("spark.logConf", "true")
      .set("spark.driver.port", driverPort.toString)
      .set("spark.driver.host", driverHost)

    val sc = new SparkContext(conf)

    val textData = sc.textFile("./README.md", 2).cache()
    val numAs = textData.filter(line => line.contains("a")).count()
    val numBs = textData.filter(line => line.contains("b")).count()

    println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
  }
}
