package de.caritas.cob.messageservice.config.apiclient;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.Collection;
import javax.ws.rs.InternalServerErrorException;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Extension of the generated UserService API client to adapt the handling of parameter values.
 */
public class UserApiClient extends de.caritas.cob.messageservice.userservice.generated.ApiClient {

  private static final String FILTER_NAME = "filter";

  public UserApiClient(RestTemplate restTemplate) {
    super(restTemplate);
  }

  /**
   * Changes the behavior of mapping multiple parameter values to exclude null values for objects
   * which are not {@link Collection} for filter query params.
   *
   * @param collectionFormat The format to convert to
   * @param name             The name of the parameter
   * @param value            The parameter's value
   * @return a Map containing non-null String value(s) of the input parameter
   */
  @Override
  public MultiValueMap<String, String> parameterToMultiValueMap(
      CollectionFormat collectionFormat, String name, Object value) {

    if ("rcGroupIds[]".equals(name)) {
      name = "rcGroupIds";
    }
    if (noValidFilterParams(name, value)) {
      return super.parameterToMultiValueMap(collectionFormat, name, value);
    }


    return obtainQueryParameters(value);
  }

  private boolean noValidFilterParams(String queryName, Object queryValue) {
    return isEmpty(queryName) || !queryName.equals(FILTER_NAME) || isNull(queryValue);
  }

  private MultiValueMap<String, String> obtainQueryParameters(Object queryValue) {
    MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();

    try {
      Arrays.asList(Introspector.getBeanInfo(queryValue.getClass(), Object.class)
          .getPropertyDescriptors())
          .stream()
          .filter(descriptor -> nonNull(descriptor.getReadMethod()))
          .forEach(descriptor -> setMethodKeyValuePairs(queryValue, paramMap, descriptor));
      return paramMap;

    } catch (IntrospectionException exception) {
      throw new InternalServerErrorException(
          String.format("Could not obtain method properties of %s", queryValue),
          exception);
    }
  }

  private void setMethodKeyValuePairs(Object queryValue, MultiValueMap<String, String> map,
      PropertyDescriptor descriptor) {
    try {
      Object value = descriptor.getReadMethod().invoke(queryValue);
      if (nonNull(value)) {
        map.add(descriptor.getName(), value.toString());
      }
    } catch (Exception exception) {
      throw new InternalServerErrorException(
          String.format("Could not obtain method key value pairs of %s", queryValue.toString()),
          exception);
    }
  }
}
