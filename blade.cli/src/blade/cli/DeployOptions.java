package blade.cli;

import aQute.lib.getopt.Arguments;
import aQute.lib.getopt.Description;
import aQute.lib.getopt.Options;

@Arguments(arg = "bundleFile")
@Description("Deploys a bundle to Liferay 7")
public interface DeployOptions extends Options {

	@Description("The jmx port to use to connect to Liferay 7")
	public int port();

	@Description("Watches the deployed file for changes and will "
			+ "automatically redeploy")
	public boolean watch();

}