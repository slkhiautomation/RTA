package Test.Automation.Utils.SendEmailSetup;

public final class SeleniumResultsJsonMapper {
	public final long line;
	public final Element elements[];
	public final String name;
	public final String description;
	public final String id;
	public final String keyword;
	public final String uri;
	public final Comment comments[];

	public SeleniumResultsJsonMapper(long line, Element[] elements, String name, String description, String id,
			String keyword, String uri, Comment[] comments) {
		this.line = line;
		this.elements = elements;
		this.name = name;
		this.description = description;
		this.id = id;
		this.keyword = keyword;
		this.uri = uri;
		this.comments = comments;
	}

	public static final class Element {
		public final long line;
		public final String name;
		public final String description;
		public final String type;
		public final String keyword;
		public final Step steps[];
		public final Before before[];
		public final String id;
		public final After after[];
		public final Tag tags[];

		public Element(long line, String name, String description, String type, String keyword, Step[] steps,
				Before[] before, String id, After[] after, Tag[] tags) {
			this.line = line;
			this.name = name;
			this.description = description;
			this.type = type;
			this.keyword = keyword;
			this.steps = steps;
			this.before = before;
			this.id = id;
			this.after = after;
			this.tags = tags;
		}

		public static final class Step {
			public final Result result;
			public final long line;
			public final String name;
			public final Match match;
			public final String keyword;

			public Step(Result result, long line, String name, Match match, String keyword) {
				this.result = result;
				this.line = line;
				this.name = name;
				this.match = match;
				this.keyword = keyword;
			}

			public static final class Result {
				public final long duration;
				public final String error_message;
				public final String status;

				public Result(long duration, String error_message, String status) {
					this.duration = duration;
					this.error_message = error_message;
					this.status = status;
				}
			}

			public static final class Match {
				public final String location;

				public Match(String location) {
					this.location = location;
				}
			}
		}

		public static final class Before {
			public final Result result;
			public final Match match;

			public Before(Result result, Match match) {
				this.result = result;
				this.match = match;
			}

			public static final class Result {
				public final long duration;
				public final String status;

				public Result(long duration, String status) {
					this.duration = duration;
					this.status = status;
				}
			}

			public static final class Match {
				public final String location;

				public Match(String location) {
					this.location = location;
				}
			}
		}

		public static final class After {
			public final Result result;
			public final Match match;

			public After(Result result, Match match) {
				this.result = result;
				this.match = match;
			}

			public static final class Result {
				public final long duration;
				public final String status;

				public Result(long duration, String status) {
					this.duration = duration;
					this.status = status;
				}
			}

			public static final class Match {
				public final String location;

				public Match(String location) {
					this.location = location;
				}
			}
		}

		public static final class Tag {
			public final long line;
			public final String name;

			public Tag(long line, String name) {
				this.line = line;
				this.name = name;
			}
		}
	}

	public static final class Comment {
		public final long line;
		public final String value;

		public Comment(long line, String value) {
			this.line = line;
			this.value = value;
		}
	}

}
