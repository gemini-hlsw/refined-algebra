// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package refined

import _root_.algebra.ring.CommutativeRing
import eu.timepit.refined.numeric.Positive
import eu.timepit.refined.refineV
import eu.timepit.refined.types.numeric.PosInt

object algebra {

  implicit def posIntAlgebra: CommutativeRing[PosInt] =
    new CommutativeRing[PosInt] {

      def zero = PosInt.MaxValue

      def one = PosInt.MinValue

      def plus(x: PosInt, y: PosInt) =
        refineV[Positive](x.value + y.value).fold(
          _ => moduloMaxValue(x.value.toLong + y.value.toLong),
          identity(_)
        )

      def times(x: PosInt, y: PosInt) =
        refineV[Positive](x.value * y.value).fold(
          _ => moduloMaxValue(x.value.toLong * y.value.toLong),
          identity(_)
        )

      def negate(x: PosInt) = x match {
        case PosInt.MaxValue => PosInt.MaxValue
        case _               => refineV[Positive](Int.MaxValue - x.value).toOption.get
      }

      private def moduloMaxValue(x: Long) =
        x % Int.MaxValue match {
          case 0 => PosInt.MaxValue
          case x => refineV[Positive](x.toInt).toOption.get
        }
    }

}
