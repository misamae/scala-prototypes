package aws

import java.io._

import com.amazonaws.regions.{Region, Regions}
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.PutObjectRequest

import scala.collection.JavaConversions._

/**
  * Created by meisam on 09/08/2016.
  */
object S3Prototype {

  def main(args: Array[String]): Unit = {

    val s3 = new AmazonS3Client()

    val region = Region.getRegion(Regions.EU_WEST_1)
    s3.setRegion(region)

    println(region)

    val bucketName = "docs-archive-dev"
    val myObjectKey = "test-meisam-first-doc"

    s3.putObject(new PutObjectRequest(bucketName, myObjectKey, createSampleFile))
  }

  private def createSampleFile: File = {
    val file: File = File.createTempFile("aws-java-sdk-", ".txt")
    file.deleteOnExit()
    val writer: Writer = new OutputStreamWriter(new FileOutputStream(file))
    writer.write("abcdefghijklmnopqrstuvwxyz\n")
    writer.write("01234567890112345678901234\n")
    writer.write("!@#$%^&*()-=[]{};':',.<>/?\n")
    writer.write("01234567890112345678901234\n")
    writer.write("abcdefghijklmnopqrstuvwxyz\n")
    writer.close()
    file
  }
}
