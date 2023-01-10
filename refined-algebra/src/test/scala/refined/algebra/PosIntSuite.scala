// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
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

  test("90973857 * 1913662923 = 1886515335") {
    val x = refineV[Positive].unsafeFrom(90973857)
    val y = refineV[Positive].unsafeFrom(1913662923)
    assertEquals(Ring.times(x, y), refineV[Positive].unsafeFrom(1886515335))
  }

}
