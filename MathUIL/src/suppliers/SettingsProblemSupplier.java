package suppliers;

import java.util.*;

import utils.*;
import utils.refs.Ref;

/**
 * @author Sam Hooper
 *
 */
public abstract class SettingsProblemSupplier implements ProblemSupplier {
	
	private List<Ref> settings;

	@Override
	public final List<Ref> settings() {
		if(settings == null)
			return Collections.emptyList();
		else
			return Collections.unmodifiableList(settings);
	}
	
	/** Adds a change action to all of the {@code newSettings} that calls {@link #settingsChanged()}.*/
	protected final void addAllSettings(Ref... newSettings) {
//		System.out.printf("[enter] addAllSettings%n");
		if(this.settings == null)
			this.settings = new ArrayList<>();
		final Runnable changeAction = this::settingsChanged;
		for(Ref setting : newSettings) {
//			System.out.printf("adding change action %s to setting=%s%n",changeAction,setting);
			setting.addChangeAction(changeAction);
		}
		Collections.addAll(settings, newSettings);
	}
}