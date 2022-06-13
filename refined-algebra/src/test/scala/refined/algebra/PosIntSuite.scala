// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package refined.algebra

import _root_.algebra.laws.RingLaws
import algebra.ring.Ring
import eu.timepit.refined.cats._
import eu.timepit.refined.numeric.Positive
import eu.timepit.refined.refineV
import eu.timepit.refined.scalacheck.numeric._
import eu.timepit.refined.types.numeric.PosInt
import munit.DisciplineSuite

class PosIntSuite extends DisciplineSuite {

  checkAll("PosInt", RingLaws[PosInt].multiplicativeSemigroup)

  test("multiplicative associativity") {
    val x = refineV[Positive](90973857).toOption.get
    val y = refineV[Positive](1171327613).toOption.get
    val z = refineV[Positive](926367060).toOption.get
    assertEquals(Ring.times(Ring.times(x, y), z), Ring.times(x, Ring.times(y, z)))
  }

}
