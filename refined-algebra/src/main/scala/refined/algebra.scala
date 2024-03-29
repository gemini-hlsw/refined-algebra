// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package refined

import _root_.algebra.ring.CommutativeRing
import eu.timepit.refined.numeric.Positive
import eu.timepit.refined.refineV
import eu.timepit.refined.types.numeric.PosInt

package object algebra {

  implicit def posIntAlgebra: CommutativeRing[PosInt] =
    new CommutativeRing[PosInt] {

      def zero = PosInt.MaxValue

      def one = PosInt.MinValue

      def plus(x: PosInt, y: PosInt) = {
        val z = Integer.remainderUnsigned(x.value + y.value, Int.MaxValue)
        if (z == 0) PosInt.MaxValue else refineV[Positive].unsafeFrom(z)
      }

      def times(x: PosInt, y: PosInt) =
        (x.value.toLong * y.value.toLong) % Int.MaxValue match {
          case 0 => PosInt.MaxValue
          case z => refineV[Positive].unsafeFrom(z.toInt)
        }

      def negate(x: PosInt) = x match {
        case PosInt.MaxValue => PosInt.MaxValue
        case _               => refineV[Positive].unsafeFrom(Int.MaxValue - x.value)
      }

    }

}
