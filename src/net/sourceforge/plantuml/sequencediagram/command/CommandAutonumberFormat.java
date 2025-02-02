package net.sourceforge.plantuml.sequencediagram.command;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.ParserPass;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.sequencediagram.DottedNumber;
import net.sourceforge.plantuml.sequencediagram.SequenceDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

import java.text.DecimalFormat;

public class CommandAutonumberFormat extends SingleLineCommand2<SequenceDiagram> {


	public CommandAutonumberFormat() {
		super(getConcat());
	}

	private static RegexConcat getConcat() {
		return RegexConcat.build(CommandAutonumberIncrement.class.getName(), RegexLeaf.start(), //
						new RegexLeaf("autonumber"), //
						RegexLeaf.spaceOneOrMore(), //
						new RegexLeaf("format"), //
						RegexLeaf.spaceOneOrMore(), //
						new RegexLeaf("FORMAT", "([0-9])"), //
						RegexLeaf.spaceZeroOrMore(), RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(SequenceDiagram diagram, LineLocation location, RegexResult arg, ParserPass currentPass) {
//		final String format = arg.get("FORMAT", 0);
//		if (format == null) {
//			return CommandExecutionResult.error("No format passed!");
//		} else {
//			final int pos = Integer.parseInt(format);
		DottedNumber start = DottedNumber.create("3");
			DecimalFormat decimalFormat = new DecimalFormat("<b>#0.0000</b>");
			int inc = Integer.parseInt("4");
			String currentMessageNumber = diagram.getAutoNumber().getCurrentMessageNumber(false);
			diagram.getAutoNumber().resume(inc, decimalFormat);

//		}
		return CommandExecutionResult.ok();
	}

}
