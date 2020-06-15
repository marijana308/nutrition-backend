package diplomski.nutrition.entity;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//
//import diplomski.nutrition.enumeration.QueryType;

//@Entity //Query for the nutritionix API
//public class Query {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	@Column(name="query", unique=false, nullable=false)
//	private String query;
//	
//	@Column(name="query_type", unique=false, nullable=false)
//	private QueryType queryType;
//
//	public Query() {
//		super();
//	}
//
//	public Query(Long id, String query, QueryType queryType) {
//		super();
//		this.id = id;
//		this.query = query;
//		this.queryType = queryType;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getQuery() {
//		return query;
//	}
//
//	public void setQuery(String query) {
//		this.query = query;
//	}
//
//	public QueryType getQueryType() {
//		return queryType;
//	}
//
//	public void setQueryType(QueryType queryType) {
//		this.queryType = queryType;
//	}
//}
