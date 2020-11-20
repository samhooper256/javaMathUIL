package suppliers;

import utils.refs.Ref;

/**
 * @author Sam Hooper
 *
 */
public interface NamedSetting<T extends Ref> extends Ref {
	
	String getName();
	
	T ref();
	
	public static <T extends Ref> NamedSetting<T> of(final T ref, final String name) {
		return new NamedSetting<>() {
			
			@Override
			public String getName() {
				return name;
			}
			
			@Override
			public T ref() {
				return ref;
			}
			
			@Override
			public String toString() {
				return String.format("NamedSetting[name=%s, ref=%s]", name, ref);
			}
		};
	}
}
