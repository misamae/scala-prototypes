package pdf

import java.io.File

import org.apache.pdfbox.io.RandomAccessFile
import org.apache.pdfbox.pdfparser.PDFParser
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

/**
  * Created by meisam on 17/08/2016.
  */
object PdfReaderPrototype {

  def main(args: Array[String]): Unit = {
    val fileName = "/LPG_20110131.pdf"
//    firstTry(fileName)

    val pdf = PDDocument.load(getClass.getResourceAsStream(fileName))
    val stripper = new PDFTextStripper
    stripper.setStartPage(1)
    stripper.setEndPage(1)
    val text = stripper.getText(pdf)

    println(text)


  }

  def firstTry(fileName: String): Unit = {
    val file = new File(s"/Users/meisam/code/scala-junk/src/main/resources/$fileName")
    val parser = new PDFParser(new RandomAccessFile(file, "r"))

    val cosDoc = parser.getDocument
    require(cosDoc != null, "cannot be null")
    val stripper = new PDFTextStripper
    val pdfDoc = new PDDocument(cosDoc)
    require(pdfDoc != null, "cannot be null")
    stripper.setStartPage(1)
    stripper.setEndPage(4)

    val text = stripper.getText(pdfDoc)
    println(text)
  }
}
