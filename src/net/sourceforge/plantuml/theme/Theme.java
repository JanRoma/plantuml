/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2021, Arnaud Roques
 *
 * Project Info:  https://plantuml.com
 *
 * If you like this project or if you find it useful, you can support us at:
 *
 * https://plantuml.com/patreon (only 1$ per month!)
 * https://plantuml.com/paypal
 *
 * This file is part of PlantUML.
 *
 * PlantUML is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PlantUML distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 *
 * Original Author:  Arnaud Roques
 *
 *
 */
package net.sourceforge.plantuml.theme;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.preproc.ReadLine;
import net.sourceforge.plantuml.text.StringLocated;

public class Theme {

	private final ReadLine source;

	// Right now, the header is not used
	// It will be used in next releases
	private List<String> header;

	public Theme(ReadLine source) {
		this.source = source;
	}

	public StringLocated readLine() throws IOException {
		StringLocated line = source.readLine();
		if (header == null) {
			// At this point, "line" is the very first line of the file
			header = new ArrayList<String>();
			if (isSeparator(line)) {
				do {
					line = source.readLine();
					if (line == null || isSeparator(line))
						break;
					header.add(line.getString());
				} while (true);
				// Skip the second separator
				line = source.readLine();
			}

		}
		return line;
	}

	private boolean isSeparator(StringLocated line) {
		return line.getString().equals("---");
	}

	public void close() throws IOException {
		source.close();
	}

}