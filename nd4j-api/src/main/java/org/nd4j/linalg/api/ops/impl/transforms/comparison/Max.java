/*
 * Copyright 2015 Skymind,Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.nd4j.linalg.api.ops.impl.transforms.comparison;

import org.apache.commons.math3.util.FastMath;
import org.nd4j.linalg.api.complex.IComplexNumber;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.BaseTransformOp;
import org.nd4j.linalg.api.ops.Op;
import org.nd4j.linalg.factory.Nd4j;

/**
 * Max function
 *
 * @author Adam Gibson
 */
public class Max extends BaseTransformOp {
    public Max(INDArray x, INDArray y, INDArray z, int n) {
        super(x, y, z, n);
    }

    public Max(INDArray x) {
        super(x);
    }

    public Max(INDArray ndArray, INDArray dup) {
        super(ndArray, dup);
    }

    public Max(INDArray x, INDArray z, int n) {
        super(x, z, n);
    }

    @Override
    public String name() {
        return "max";
    }

    @Override
    public IComplexNumber op(IComplexNumber origin, double other) {
        double val = origin.absoluteValue().doubleValue();
        return val < other ? origin : Nd4j.createComplexNumber(other, 0.0);
    }

    @Override
    public IComplexNumber op(IComplexNumber origin, float other) {
        float val = origin.absoluteValue().floatValue();
        return val < other ? origin : Nd4j.createComplexNumber(other, 0.0);

    }

    @Override
    public IComplexNumber op(IComplexNumber origin, IComplexNumber other) {
        return origin.absoluteValue().doubleValue() < other.absoluteValue().doubleValue() ? origin : other;
    }

    @Override
    public float op(float origin, float other) {
        return FastMath.max(origin, other);
    }

    @Override
    public double op(double origin, double other) {
        return FastMath.max(origin, other);
    }

    @Override
    public double op(double origin) {
        return origin;
    }

    @Override
    public float op(float origin) {
        return origin;
    }

    @Override
    public IComplexNumber op(IComplexNumber origin) {
        return origin;
    }


    @Override
    public Op opForDimension(int index, int dimension) {
        INDArray xAlongDimension = x.vectorAlongDimension(index, dimension);

        if (y() != null)
            return new Max(xAlongDimension, y.vectorAlongDimension(index, dimension), z.vectorAlongDimension(index, dimension), xAlongDimension.length());
        else
            return new Max(xAlongDimension, z.vectorAlongDimension(index, dimension), xAlongDimension.length());

    }
}
