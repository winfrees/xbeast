/*
 * SpaceTimeRejector.java
 *
 * Copyright (c) 2002-2015 Alexei Drummond, Andrew Rambaut and Marc Suchard
 *
 * This file is part of BEAST.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership and licensing.
 *
 * BEAST is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 *  BEAST is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with BEAST; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor,
 * Boston, MA  02110-1301  USA
 */

package dr.geo;

import java.awt.geom.Rectangle2D;
import java.util.List;

/**
 * @author
 */
public interface SpaceTimeRejector {

    boolean reject(double time, double[] space);

    // removes all rejects
    void reset();

    List<Reject> getRejects();

    class Utils {
        public static SpaceTimeRejector createSimpleBounds2D(final Rectangle2D bounds) {

            return new SpaceTimeRejector() {

                public boolean reject(double time, double[] space) {
                    return !bounds.contains(space[0], space[1]);
                }

                public void reset() {
                    //To change body of implemented methods use File | Settings | File Templates.
                }

                public List<Reject> getRejects() {
                    return null;  //To change body of implemented methods use File | Settings | File Templates.
                }
            };
        }
    }
}