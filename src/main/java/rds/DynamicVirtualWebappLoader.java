package rds;

import org.apache.catalina.WebResource;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.FileResource;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import java.io.File;

public class DynamicVirtualWebappLoader extends DirResourceSet
{

    private static final Log log = LogFactory.getLog(DynamicVirtualWebappLoader.class);

    public DynamicVirtualWebappLoader() {
        super();
    }

    public DynamicVirtualWebappLoader(WebResourceRoot root, String webAppMount, String base, String internalPath) {
        super(root, webAppMount, base, internalPath);
    }

    @Override
    public WebResource getResource(String path) {

        /*
        <Resources>
		    <PostResources className="rds.DynamicVirtualWebappLoader"
                   webAppMount="/WEB-INF/classes" base="C:/WebPrograms/we-ql-service/config" />
	    </Resources>
	*/

        String mountPath = getWebAppMount();
        // strip the webAppMount out of the path so we can get the relative path of what we are looking for
        String resource = path.replace(mountPath, "");
        String contextName = getRoot().getContext().getBaseName();

        File customResourceDirFile = new File(getBase(), contextName);
        File resourceFile = new File(customResourceDirFile, resource);
        boolean found = resourceFile.exists();

        if (log.isDebugEnabled())
            log.debug("Resource Path = " + resource + " Context = " + contextName +
                    " Resource Dir = " + customResourceDirFile.getAbsolutePath() + " Found = " + found);

        if(found) {
            return new FileResource(getRoot(), path, resourceFile, isReadOnly(), getManifest());
        }

        return super.getResource(path);
    }

}
