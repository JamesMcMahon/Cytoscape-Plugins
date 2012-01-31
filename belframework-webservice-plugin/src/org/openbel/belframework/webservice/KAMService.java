package org.openbel.belframework.webservice;

import java.util.List;

import com.selventa.belframework.ws.client.BelStatement;
import com.selventa.belframework.ws.client.BelTerm;
import com.selventa.belframework.ws.client.EdgeDirectionType;
import com.selventa.belframework.ws.client.EdgeFilter;
import com.selventa.belframework.ws.client.FunctionType;
import com.selventa.belframework.ws.client.Kam;
import com.selventa.belframework.ws.client.KamEdge;
import com.selventa.belframework.ws.client.KamHandle;
import com.selventa.belframework.ws.client.KamNode;
import com.selventa.belframework.ws.client.NodeFilter;
import com.selventa.belframework.ws.client.WebAPI;

/**
 * {@link KAMService} defines an API wrapper around version 1.2.3 of the
 * {@link WebAPI BEL Framework Web API}.
 *
 * @author Anthony Bargnesi &lt;abargnesi@selventa.com&gt;
 */
public interface KAMService {

	/**
	 * Retrieves the KAM catalog for the configured BELFramework.
	 *
	 * @return the {@link List} of {@link Kam kams} from the KAM catalog, which
	 * will not be {@code null} but might be empty
	 */
	public abstract List<Kam> getCatalog();

	/**
	 * Loads the {@link Kam kam} on the server end of the webservice and
	 * returns a {@link KamHandle kam handle} for subsequent operations.
	 *
	 * @param kam the {@link Kam kam} to load
	 * @return the {@link KamHandle kam handle} for this kam, or {@code null}
	 * if the kam does not exist in the catalog or it fails to load
	 * @throws IllegalArgumentException Thrown if the {@code kam} parameter is
	 * {@code null}
	 * @see DefaultKAMService#getCatalog()
	 */
	public abstract KamHandle loadKam(final Kam kam);

	/**
	 * Retrieves the supporting {@link BelTerm BEL terms} for a specific
	 * {@link KamNode kam node}.
	 *
	 * @param node the {@link KamNode kam node}
	 * @return the {@link List} of {@link BelTerm BEL terms} that back the
	 * {@link KamNode kam node}, which will not be {@code null} and must
	 * contain at least one entry
	 * @throws IllegalArgumentException Thrown if the {@code node} parameter is
	 * {@code null}
	 */
	public abstract List<BelTerm> getSupportingTerms(final KamNode node);

	/**
	 * Retrieves the supporting {@link BelStatement statements} for a specific
	 * {@link KamEdge kam edge}.
	 *
	 * @param edge the {@link KamEdge kam edge}
	 * @return the {@link List} of {@link BelStatement statements} that back
	 * the {@link KamEdge kam edge}, which will not be {@code null} and must
	 * contain at least one entry
	 * @throws IllegalArgumentException Thrown if the {@code kam} parameter is
	 * {@code null}
	 */
	public abstract List<BelStatement> getSupportingEvidence(final KamEdge edge);

	/**
	 * Finds {@link KamNode kam nodes} by {@link FunctionType BEL function} for
	 * a specific loaded kam.
	 *
	 * @param handle the {@link KamHandle kam handle} that identifies a loaded
	 * {@link Kam}
	 * @param function the {@link FunctionType BEL function} to find by
	 * @return the {@link List} of {@link KamNode kam nodes}, which will not be
	 * {@code null} but may be empty
	 * @throws IllegalArgumentException Thrown if the {@code handle} or
	 * {@code function} parameter is {@code null}
	 */
	public abstract List<KamNode> findKamNodesByFunction(
			final KamHandle handle, final FunctionType function);

	/**
	 * Finds {@link KamNode kam nodes} by a regular expression pattern and
	 * optional {@link NodeFilter node filter}.
	 *
	 * @param handle the {@link KamHandle kam handle} that identifies a loaded
	 * {@link Kam kam}
	 * @param regex the regular expression {@link String} to find by
	 * @param nf the optional {@link NodeFilter node filter} to further
	 * restrict the results
	 * @return the {@link List} of {@link KamNode kam nodes}, which will not be
	 * {@code null} but may be empty
	 * @throws IllegalArgumentException Thrown if the {@code handle} or
	 * {@code regex} parameter is {@code null}
	 */
	public abstract List<KamNode> findKamNodesByPatterns(
			final KamHandle handle, final String regex, final NodeFilter nf);

	/**
	 * Retrieves {@link KamEdge kam edges} that are adjacent to a
	 * {@link KamNode kam node} either in the outgoing, incoming, or both
	 * directions.  Optionally an {@link EdgeFilter edge filter} can be used to
	 * further restrict the results
	 *
	 * @param node the {@link KamNode kam node} to find adjacent
	 * {@link KamEdge kam edges} for
	 * @param direction the {@link EdgeDirectionType edge direction}
	 * @param ef the optional {@link EdgeFilter edge filter}
	 * @return the {@link List} of {@link KamEdge kam edges}, which will not be
	 * {@code null}, but may be empty
	 * @throws IllegalArgumentException Thrown if the {@code node} or
	 * {@code direction} parameter is {@code null}
	 */
	public abstract List<KamEdge> getAdjacentKamEdges(final KamNode node,
			final EdgeDirectionType direction, final EdgeFilter ef);

}