package org.xmlresolver.catalog.entry;

import org.xmlresolver.ResolverConfiguration;
import org.xmlresolver.utils.URIUtils;

import java.net.URI;

public class EntryDelegateSystem extends Entry {
    public final String systemIdStart;
    public final URI catalog;

    public EntryDelegateSystem(ResolverConfiguration config, URI baseURI, String id, String start, String catalog) {
        super(config, baseURI, id);

        if (start.startsWith("classpath:/")) {
            // classpath:/path/to/thing is the same as classpath:path/to/thing
            // normalize without the leading slash.
            systemIdStart = "classpath:" + start.substring(11);
        } else {
            systemIdStart = start;
        }

        this.catalog = URIUtils.resolve(baseURI, catalog);
    }

    @Override
    public Type getType() {
        return Type.DELEGATE_SYSTEM;
    }

    @Override
    public String toString() {
        return "delegateSystem " + systemIdStart + Entry.rarr + catalog;
    }
}
