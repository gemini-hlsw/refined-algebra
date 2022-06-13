// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package refined.algebra

import _root_.algebra.laws.RingLaws
import eu.timepit.refined.cats._
import eu.timepit.refined.scalacheck.numeric._
import eu.timepit.refined.types.numeric.PosInt
import munit.DisciplineSuite

class PosIntSuite extends DisciplineSuite {

  checkAll("PosInt", RingLaws[PosInt].commutativeRing)

}
