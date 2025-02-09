package org.xmlresolver.catalog.entry;

import org.xmlresolver.ResolverConfiguration;
import org.xmlresolver.utils.URIUtils;

import java.net.URI;

public class EntryRewriteSystem extends Entry {
    public final String systemIdStart;
    public final URI rewritePrefix;

    public EntryRewriteSystem(ResolverConfiguration config, URI baseURI, String id, String start, String rewrite) {
        super(config, baseURI, id);

        if (start.startsWith("classpath:/")) {
            // classpath:/path/to/thing is the same as classpath:path/to/thing
            // normalize without the leading slash.
            systemIdStart = "classpath:" + start.substring(11);
        } else {
            systemIdStart = start;
        }

        rewritePrefix = URIUtils.resolve(baseURI, rewrite);
    }

    @Override
    public Type getType() {
        return Type.REWRITE_SYSTEM;
    }

    @Override
    public String toString() {
        return "rewriteSystem " + systemIdStart + Entry.rarr + rewritePrefix;
    }
}
