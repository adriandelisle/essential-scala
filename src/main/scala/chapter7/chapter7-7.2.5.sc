object chapter7_7_2_5 {

  // 7.2.5.1 Ordering Orders
  // Here is a case class to store orders of some arbitrary item.
  final case class Order(units: Int, unitPrice: Double) {
    val totalPrice: Double = units * unitPrice
  }

  object OrderTotalPriceOrdering {
    implicit val ordering: Ordering[Order] = Ordering.fromLessThan[Order]((a, b) =>
      a.totalPrice < b.totalPrice
    )
  }

  object OrderNumberOfUnitsOrdering {
    implicit val ordering: Ordering[Order] = Ordering.fromLessThan[Order]((a, b) =>
      a.units < b.units
    )
  }

  object OrderUnitPriceOrdering {
    implicit val ordering: Ordering[Order] = Ordering.fromLessThan[Order]((a, b) =>
      a.unitPrice < b.unitPrice
    )
  }

  // I put the orderings in separate packages because there is no single good default in my mind so importing them
  // explicitly should work out better.

  // We have a requirement to order Orders in three different ways:
  //  1. by totalPrice;
  //  2. by number of units; and
  //  3. by unitPrice.
  // Implement and package implicits to provide these orderings, and justify your packaging.
}

chapter7_7_2_5
