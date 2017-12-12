package jfreerails.client.common;


/**
 * Classes may implement this interface if they have a section of code that
 * needs to be locked an arbitrary number of times.
 */
public interface MultiLockedRegion {
    public void multiLockedCallback();
}