/* Generated By:JJTree: Do not edit this line. OExpression.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.orientechnologies.orient.core.sql.parser;

import com.orientechnologies.orient.core.collate.OCollate;
import com.orientechnologies.orient.core.command.OCommandContext;
import com.orientechnologies.orient.core.db.record.OIdentifiable;
import com.orientechnologies.orient.core.exception.OCommandExecutionException;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.record.OElement;
import com.orientechnologies.orient.core.sql.executor.AggregationContext;
import com.orientechnologies.orient.core.sql.executor.OResult;
import com.orientechnologies.orient.core.sql.executor.OResultInternal;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class OExpression extends SimpleNode {

  protected Boolean singleQuotes;
  protected Boolean doubleQuotes;

  protected boolean                isNull = false;
  protected ORid                   rid;
  protected OMathExpression        mathExpression;
  protected OArrayConcatExpression arrayConcatExpression;
  protected OJson                  json;
  protected Boolean                booleanValue;

  public OExpression(int id) {
    super(id);
  }

  public OExpression(OrientSql p, int id) {
    super(p, id);
  }

  public OExpression(OIdentifier identifier) {
    mathExpression = new OBaseExpression(identifier);
  }

  public OExpression(OIdentifier identifier, OModifier modifier) {

    mathExpression = new OBaseExpression(identifier, modifier);
  }

  public OExpression(ORecordAttribute attr, OModifier modifier) {
    mathExpression = new OBaseExpression(attr, modifier);
  }

  /**
   * Accept the visitor.
   **/
  public Object jjtAccept(OrientSqlVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }

  public Object execute(OIdentifiable iCurrentRecord, OCommandContext ctx) {
    if (isNull) {
      return null;
    }
    if (rid != null) {
      return rid.toRecordId(iCurrentRecord, ctx);
    }
    if (mathExpression != null) {
      return mathExpression.execute(iCurrentRecord, ctx);
    }
    if (arrayConcatExpression != null) {
      return arrayConcatExpression.execute(iCurrentRecord, ctx);
    }
    if (json != null) {
      return json.toMap(iCurrentRecord, ctx);
    }
    if (booleanValue != null) {
      return booleanValue;
    }
    if (value instanceof ONumber) {
      return ((ONumber) value).getValue();//only for old executor (manually replaced params)
    }

    //from here it's old stuff, only for the old executor
    if (value instanceof ORid) {
      ORid v = (ORid) value;
      return new ORecordId(v.cluster.getValue().intValue(), v.position.getValue().longValue());
    } else if (value instanceof OMathExpression) {
      return ((OMathExpression) value).execute(iCurrentRecord, ctx);
    } else if (value instanceof OArrayConcatExpression) {
      return ((OArrayConcatExpression) value).execute(iCurrentRecord, ctx);
    } else if (value instanceof OJson) {
      return ((OJson) value).toMap(iCurrentRecord, ctx);
    } else if (value instanceof String) {
      return value;
    } else if (value instanceof Number) {
      return value;
    }

    return value;
  }

  public Object execute(OResult iCurrentRecord, OCommandContext ctx) {
    if (isNull) {
      return null;
    }
    if (rid != null) {
      return rid.toRecordId(iCurrentRecord, ctx);
    }
    if (mathExpression != null) {
      return mathExpression.execute(iCurrentRecord, ctx);
    }
    if (arrayConcatExpression != null) {
      return arrayConcatExpression.execute(iCurrentRecord, ctx);
    }
    if (json != null) {
      return json.toMap(iCurrentRecord, ctx);
    }
    if (booleanValue != null) {
      return booleanValue;
    }
    if (value instanceof ONumber) {
      return ((ONumber) value).getValue();//only for old executor (manually replaced params)
    }

    //from here it's old stuff, only for the old executor
    if (value instanceof ORid) {
      ORid v = (ORid) value;
      return new ORecordId(v.cluster.getValue().intValue(), v.position.getValue().longValue());
    } else if (value instanceof OMathExpression) {
      return ((OMathExpression) value).execute(iCurrentRecord, ctx);
    } else if (value instanceof OArrayConcatExpression) {
      return ((OArrayConcatExpression) value).execute(iCurrentRecord, ctx);
    } else if (value instanceof OJson) {
      return ((OJson) value).toMap(iCurrentRecord, ctx);
    } else if (value instanceof String) {
      return value;
    } else if (value instanceof Number) {
      return value;
    }

    return value;
  }

  public boolean isBaseIdentifier() {
    if (mathExpression != null) {
      return mathExpression.isBaseIdentifier();
    }
    if (value instanceof OMathExpression) {//only backward stuff, remote it
      return ((OMathExpression) value).isBaseIdentifier();
    }

    return false;
  }

  public boolean isEarlyCalculated() {
    if (this.mathExpression != null) {
      return this.mathExpression.isEarlyCalculated();
    }
    if (this.arrayConcatExpression != null) {
      return this.arrayConcatExpression.isEarlyCalculated();
    }

    if (booleanValue != null) {
      return true;
    }
    if (value instanceof Number) {
      return true;
    }
    if (value instanceof String) {
      return true;
    }
    if (value instanceof OMathExpression) {
      return ((OMathExpression) value).isEarlyCalculated();
    }

    return false;
  }

  public OIdentifier getDefaultAlias() {
    OIdentifier identifier;
    if (isBaseIdentifier()) {
      identifier = new OIdentifier(((OBaseExpression) mathExpression).identifier.getSuffix().identifier.getStringValue());
    } else {
      identifier = new OIdentifier(this.toString());
    }
    return identifier;
  }

  public void toString(Map<Object, Object> params, StringBuilder builder) {
    //    if (value == null) {
    //      builder.append("null");
    //    } else if (value instanceof SimpleNode) {
    //      ((SimpleNode) value).toString(params, builder);
    //    } else if (value instanceof String) {
    //      if (Boolean.TRUE.equals(singleQuotes)) {
    //        builder.append("'" + value + "'");
    //      } else {
    //        builder.append("\"" + value + "\"");
    //      }
    //    } else {
    //      builder.append("" + value);
    //    }

    if (isNull) {
      builder.append("null");
    } else if (rid != null) {
      rid.toString(params, builder);
    } else if (mathExpression != null) {
      mathExpression.toString(params, builder);
    } else if (arrayConcatExpression != null) {
      arrayConcatExpression.toString(params, builder);
    } else if (json != null) {
      json.toString(params, builder);
    } else if (booleanValue != null) {
      builder.append(booleanValue.toString());
    } else if (value instanceof SimpleNode) {
      ((SimpleNode) value).toString(params, builder);//only for translated input params, will disappear with new executor
    } else if (value instanceof String) {
      if (Boolean.TRUE.equals(singleQuotes)) {
        builder.append("'" + value + "'");
      } else {
        builder.append("\"" + value + "\"");
      }

    } else {
      builder.append("" + value);//only for translated input params, will disappear with new executor
    }
  }

  public static String encode(String s) {
    StringBuilder builder = new StringBuilder(s.length());
    for (char c : s.toCharArray()) {
      if (c == '\n') {
        builder.append("\\n");
        continue;
      }
      if (c == '\t') {
        builder.append("\\t");
        continue;
      }
      if (c == '\\' || c == '"') {
        builder.append("\\");
      }
      builder.append(c);
    }
    return builder.toString();
  }

  public boolean supportsBasicCalculation() {
    if (mathExpression != null) {
      return mathExpression.supportsBasicCalculation();
    }
    if (arrayConcatExpression != null) {
      return arrayConcatExpression.supportsBasicCalculation();
    }
    return true;
  }

  public boolean isIndexedFunctionCal() {
    if (mathExpression != null) {
      return mathExpression.isIndexedFunctionCall();
    }
    return false;
  }

  public static String encodeSingle(String s) {

    StringBuilder builder = new StringBuilder(s.length());
    for (char c : s.toCharArray()) {
      if (c == '\n') {
        builder.append("\\n");
        continue;
      }
      if (c == '\t') {
        builder.append("\\t");
        continue;
      }
      if (c == '\\' || c == '\'') {
        builder.append("\\");
      }
      builder.append(c);
    }
    return builder.toString();
  }

  public long estimateIndexedFunction(OFromClause target, OCommandContext context, OBinaryCompareOperator operator, Object right) {
    if (mathExpression != null) {
      return mathExpression.estimateIndexedFunction(target, context, operator, right);
    }
    return -1;
  }

  public Iterable<OIdentifiable> executeIndexedFunction(OFromClause target, OCommandContext context,
      OBinaryCompareOperator operator, Object right) {
    if (mathExpression != null) {
      return mathExpression.executeIndexedFunction(target, context, operator, right);
    }
    return null;
  }

  /**
   * tests if current expression is an indexed function AND that function can also be executed without using the index
   *
   * @param target   the query target
   * @param context  the execution context
   * @param operator
   * @param right
   *
   * @return true if current expression is an indexed funciton AND that function can also be executed without using the index, false
   * otherwise
   */
  public boolean canExecuteIndexedFunctionWithoutIndex(OFromClause target, OCommandContext context, OBinaryCompareOperator operator,
      Object right) {
    if (mathExpression != null) {
      return mathExpression.canExecuteIndexedFunctionWithoutIndex(target, context, operator, right);
    }
    return false;
  }

  /**
   * tests if current expression is an indexed function AND that function can be used on this target
   *
   * @param target   the query target
   * @param context  the execution context
   * @param operator
   * @param right
   *
   * @return true if current expression involves an indexed function AND that function can be used on this target, false otherwise
   */
  public boolean allowsIndexedFunctionExecutionOnTarget(OFromClause target, OCommandContext context,
      OBinaryCompareOperator operator, Object right) {
    if (mathExpression != null) {
      return mathExpression.allowsIndexedFunctionExecutionOnTarget(target, context, operator, right);
    }
    return false;
  }

  /**
   * tests if current expression is an indexed function AND the function has also to be executed after the index search. In some
   * cases, the index search is accurate, so this condition can be excluded from further evaluation. In other cases the result from
   * the index is a superset of the expected result, so the function has to be executed anyway for further filtering
   *
   * @param target  the query target
   * @param context the execution context
   *
   * @return true if current expression involves an indexed function AND the function has also to be executed after the index
   * search.
   */
  public boolean executeIndexedFunctionAfterIndexSearch(OFromClause target, OCommandContext context,
      OBinaryCompareOperator operator, Object right) {
    if (mathExpression != null) {
      return mathExpression.executeIndexedFunctionAfterIndexSearch(target, context, operator, right);
    }
    return false;
  }

  public boolean isExpand() {
    if (mathExpression != null) {
      return mathExpression.isExpand();
    }
    return false;
  }

  public OExpression getExpandContent() {
    return mathExpression.getExpandContent();
  }

  public boolean needsAliases(Set<String> aliases) {
    if (mathExpression != null) {
      return mathExpression.needsAliases(aliases);
    }
    if (arrayConcatExpression != null) {
      return arrayConcatExpression.needsAliases(aliases);
    }
    if (json != null) {
      return json.needsAliases(aliases);
    }
    return false;
  }

  public boolean isAggregate() {
    if (mathExpression != null && mathExpression.isAggregate()) {
      return true;
    }
    if (arrayConcatExpression != null && arrayConcatExpression.isAggregate()) {
      return true;
    }
    if (json != null && json.isAggregate()) {
      return true;
    }
    return false;
  }

  public OExpression splitForAggregation(AggregateProjectionSplit aggregateSplit) {
    if (isAggregate()) {
      OExpression result = new OExpression(-1);
      if (mathExpression != null) {
        SimpleNode splitResult = mathExpression.splitForAggregation(aggregateSplit);
        if (splitResult instanceof OMathExpression) {
          result.mathExpression = (OMathExpression) splitResult;
        } else if (splitResult instanceof OExpression) {
          return (OExpression) splitResult;
        } else {
          throw new IllegalStateException("something went wrong while splitting expression for aggregate " + toString());
        }
      }
      if (arrayConcatExpression != null) {
        SimpleNode splitResult = arrayConcatExpression.splitForAggregation(aggregateSplit);
        if (splitResult instanceof OArrayConcatExpression) {
          result.arrayConcatExpression = (OArrayConcatExpression) splitResult;
        } else if (splitResult instanceof OExpression) {
          return (OExpression) splitResult;
        } else {
          throw new IllegalStateException("something went wrong while splitting expression for aggregate " + toString());
        }
      }
      if (json != null) {
        result.json = json.splitForAggregation(aggregateSplit);
      }
      return result;
    } else {
      return this;
    }
  }

  public AggregationContext getAggregationContext(OCommandContext ctx) {
    if (mathExpression != null) {
      return mathExpression.getAggregationContext(ctx);
    } else if (arrayConcatExpression != null) {
      return arrayConcatExpression.getAggregationContext(ctx);
    } else {
      throw new OCommandExecutionException("Cannot aggregate on " + toString());
    }
  }

  public OExpression copy() {

    OExpression result = new OExpression(-1);
    result.singleQuotes = singleQuotes;
    result.doubleQuotes = doubleQuotes;
    result.isNull = isNull;
    result.rid = rid == null ? null : rid.copy();
    result.mathExpression = mathExpression == null ? null : mathExpression.copy();
    result.arrayConcatExpression = arrayConcatExpression == null ? null : arrayConcatExpression.copy();
    result.json = json == null ? null : json.copy();
    result.booleanValue = booleanValue;

    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    OExpression that = (OExpression) o;

    if (isNull != that.isNull)
      return false;
    if (singleQuotes != null ? !singleQuotes.equals(that.singleQuotes) : that.singleQuotes != null)
      return false;
    if (doubleQuotes != null ? !doubleQuotes.equals(that.doubleQuotes) : that.doubleQuotes != null)
      return false;
    if (rid != null ? !rid.equals(that.rid) : that.rid != null)
      return false;
    if (mathExpression != null ? !mathExpression.equals(that.mathExpression) : that.mathExpression != null)
      return false;
    if (arrayConcatExpression != null ?
        !arrayConcatExpression.equals(that.arrayConcatExpression) :
        that.arrayConcatExpression != null)
      return false;
    if (json != null ? !json.equals(that.json) : that.json != null)
      return false;
    if (booleanValue != null ? !booleanValue.equals(that.booleanValue) : that.booleanValue != null)
      return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = singleQuotes != null ? singleQuotes.hashCode() : 0;
    result = 31 * result + (doubleQuotes != null ? doubleQuotes.hashCode() : 0);
    result = 31 * result + (isNull ? 1 : 0);
    result = 31 * result + (rid != null ? rid.hashCode() : 0);
    result = 31 * result + (mathExpression != null ? mathExpression.hashCode() : 0);
    result = 31 * result + (arrayConcatExpression != null ? arrayConcatExpression.hashCode() : 0);
    result = 31 * result + (json != null ? json.hashCode() : 0);
    result = 31 * result + (booleanValue != null ? booleanValue.hashCode() : 0);
    return result;
  }

  public void setMathExpression(OMathExpression mathExpression) {
    this.mathExpression = mathExpression;
  }

  public void extractSubQueries(SubQueryCollector collector) {
    if (mathExpression != null) {
      mathExpression.extractSubQueries(collector);
    }
    if (arrayConcatExpression != null) {
      arrayConcatExpression.extractSubQueries(collector);
    }
    if (json != null) {
      json.extractSubQueries(collector);
    }
  }

  public void extractSubQueries(OIdentifier letAlias, SubQueryCollector collector) {
    if (mathExpression != null) {
      mathExpression.extractSubQueries(letAlias, collector);
    }
    if (arrayConcatExpression != null) {
      arrayConcatExpression.extractSubQueries(collector);
    }
    if (json != null) {
      json.extractSubQueries(collector);
    }
  }

  public boolean refersToParent() {
    if (mathExpression != null && mathExpression.refersToParent()) {
      return true;
    }
    if (arrayConcatExpression != null && arrayConcatExpression.refersToParent()) {
      return true;
    }
    if (json != null && json.refersToParent()) {
      return true;
    }
    return false;
  }

  public ORid getRid() {
    return rid;
  }

  public void setRid(ORid rid) {
    this.rid = rid;
  }

  public OMathExpression getMathExpression() {
    return mathExpression;
  }

  /**
   * if the condition involved the current pattern (MATCH statement, eg. $matched.something = foo), returns the name of involved
   * pattern aliases ("something" in this case)
   *
   * @return a list of pattern aliases involved in this condition. Null it does not involve the pattern
   */
  List<String> getMatchPatternInvolvedAliases() {
    if (mathExpression != null)
      return mathExpression.getMatchPatternInvolvedAliases();
    if (arrayConcatExpression != null)
      return arrayConcatExpression.getMatchPatternInvolvedAliases();
    return null;
  }

  public void applyRemove(OResultInternal result, OCommandContext ctx) {
    if (mathExpression != null) {
      mathExpression.applyRemove(result, ctx);
    } else {
      throw new OCommandExecutionException("Cannot apply REMOVE " + toString());
    }
  }

  public boolean isCount() {
    if (mathExpression == null) {
      return false;
    }
    return mathExpression.isCount();
  }

  public OArrayConcatExpression getArrayConcatExpression() {
    return arrayConcatExpression;
  }

  public void setArrayConcatExpression(OArrayConcatExpression arrayConcatExpression) {
    this.arrayConcatExpression = arrayConcatExpression;
  }

  public OResult serialize() {
    OResultInternal result = new OResultInternal();
    result.setProperty("singleQuotes", singleQuotes);
    result.setProperty("doubleQuotes", doubleQuotes);
    result.setProperty("isNull", isNull);

    if (rid != null) {
      result.setProperty("rid", rid.serialize());
    }
    if (mathExpression != null) {
      result.setProperty("mathExpression", mathExpression.serialize());
    }
    if (arrayConcatExpression != null) {
      result.setProperty("arrayConcatExpression", arrayConcatExpression.serialize());
    }
    if (json != null) {
      result.setProperty("json", json.serialize());
    }
    result.setProperty("booleanValue", booleanValue);
    return result;
  }

  public void deserialize(OResult fromResult) {
    singleQuotes = fromResult.getProperty("singleQuotes");
    doubleQuotes = fromResult.getProperty("doubleQuotes");
    isNull = fromResult.getProperty("isNull");

    if (fromResult.getProperty("rid") != null) {
      rid = new ORid(-1);
      rid.deserialize(fromResult.getProperty("rid"));
    }
    if (fromResult.getProperty("mathExpression") != null) {
      mathExpression = OMathExpression.deserializeFromResult(fromResult.getProperty("mathExpression"));
    }
    if (fromResult.getProperty("arrayConcatExpression") != null) {
      arrayConcatExpression = new OArrayConcatExpression(-1);
      arrayConcatExpression.deserialize(fromResult.getProperty("arrayConcatExpression"));
    }
    if (fromResult.getProperty("json") != null) {
      json = new OJson(-1);
      json.deserialize(fromResult.getProperty("json"));
    }
    booleanValue = fromResult.getProperty("booleanValue");
  }

  public boolean isDefinedFor(OResult currentRecord) {
    if (mathExpression != null) {
      return mathExpression.isDefinedFor(currentRecord);
    } else {
      return true;
    }
  }

  public boolean isDefinedFor(OElement currentRecord) {
    if (mathExpression != null) {
      return mathExpression.isDefinedFor(currentRecord);
    } else {
      return true;
    }
  }

  public OCollate getCollate(OResult currentRecord, OCommandContext ctx) {
    if (mathExpression != null) {
      return mathExpression.getCollate(currentRecord, ctx);
    }
    return null;
  }

  public boolean isCacheable() {
    if (mathExpression != null) {
      return mathExpression.isCacheable();
    }
    if (arrayConcatExpression != null) {
      return arrayConcatExpression.isCacheable();
    }
    if (json != null) {
      return json.isCacheable();
    }

    return true;
  }

  public boolean isIndexChain(OCommandContext ctx, OClass clazz) {
    if (mathExpression != null) {
      return mathExpression.isIndexChain(ctx, clazz);
    }
    return false;
  }
}
/* JavaCC - OriginalChecksum=9c860224b121acdc89522ae97010be01 (do not edit this line) */
