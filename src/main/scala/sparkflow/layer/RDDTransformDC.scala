package sparkflow.layer

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

import scala.reflect.ClassTag

/**
  * Created by ngoehausen on 3/23/16.
  */
private[sparkflow] class RDDTransformDC[U:ClassTag, T:ClassTag]
(val prev: DC[T],
 f: RDD[T] => RDD[U],
 hashTarget: AnyRef) extends DC[U](Seq(prev)) {

  def computeRDD(sc: SparkContext) = {
    f(prev.getRDD(sc))
  }

  override def computeHash() = {
    val className = hashTarget.getClass.getName
    println(className)
    className + prev.getHash
  }


}
