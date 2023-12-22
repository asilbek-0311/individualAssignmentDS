import java.net.{ServerSocket, Socket}
import java.io.{BufferedReader, InputStreamReader, PrintWriter}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import com.opencsv.CSVReader
import scala.collection.mutable

object Server extends App {

  val serverSocket = new ServerSocket(9999)

  // In-memory map to store ISBN-price pairs
  val isbnPriceMap = csvToMap("C:\\Users\\user\\Documents\\UNI CODING\\DS\\individualAssignment\\src\\main\\resources\\data.csv")

  println("Server is running. Waiting for clients...")

  while (true) {
    val clientSocket = serverSocket.accept()
    Future {
      handleClient(clientSocket)
    }
  }

  def handleClient(clientSocket: Socket): Unit = {
    try {
      val reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream))
      val writer = new PrintWriter(clientSocket.getOutputStream, true)

      // Read ISBN from the client
      val isbn = reader.readLine()

      // Simulate fetching the price based on ISBN from the map
      val price = getPriceByISBN(isbn)

      // Send the price back to the client
      writer.println(s"The price for ISBN $isbn is $$ $price")

      clientSocket.close()
    } catch {
      case e: Exception =>
        e.printStackTrace()
    }
  }

  def csvToMap(filePath: String): mutable.Map[String, Double] = {
    val reader = new CSVReader(scala.io.Source.fromFile(filePath).bufferedReader())
    val iterator = reader.iterator()
    val map = mutable.Map[String, Double]()

    // Skip header line
    if (iterator.hasNext) iterator.next()

    while (iterator.hasNext) {
      val row = iterator.next()
      if (row.length == 2) {
        val isbn = row(0)
        val price = row(1).toDouble
        map.put(isbn, price)
      }
    }

    map
  }

  def getPriceByISBN(isbn: String): Double = {
    // Replace this with your logic to fetch the price based on ISBN from the data source (map, database, etc.)
    isbnPriceMap.getOrElse(isbn, -1.0) // Default to 0.0 if ISBN is not found
  }
}
