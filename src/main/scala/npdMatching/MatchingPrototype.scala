package npdMatching

import java.io.FileInputStream

import org.apache.poi.ss.usermodel.Workbook

/**
  * Created by meisam on 16/08/2016.
  */
object MatchingPrototype {

  def main(args: Array[String]): Unit = {
    val path = "/Users/meisam/code/king/npd matching/npd_matching.xlsx"

    val fin = new FileInputStream(path)

    val wb = org.apache.poi.ss.usermodel.WorkbookFactory.create(fin)

    val targets = readTargets(wb)

    targets.foreach(println)

    fin.close()

    println("done.")
  }

  def readTargets(wb: Workbook): Seq[String] = {
    val sh = wb.getSheet("app annie target")

    val numberOfRows = sh.getLastRowNum - sh.getFirstRowNum
    println(s"number of rows in the second sheet $numberOfRows")

    (0 to numberOfRows).map(i => {
      sh.getRow(i).getCell(0).getStringCellValue
    })
  }

}
