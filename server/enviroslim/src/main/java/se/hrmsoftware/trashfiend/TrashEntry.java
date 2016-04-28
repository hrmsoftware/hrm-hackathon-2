package se.hrmsoftware.trashfiend;

import java.util.List;
import java.util.Objects;

public class TrashEntry {
	List<String> labels;
	List<String> series;
	List<List<Integer>> data;

	public TrashEntry(List<String> labels, List<String> series, List<List<Integer>> data) {
		this.labels = labels;
		this.series = series;
		this.data = data;
	}

	public List<String> getLabels() {
		return labels;
	}

	public List<String> getSeries() {
		return series;
	}

	public List<List<Integer>> getData() {
		return data;
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		TrashEntry that = (TrashEntry) o;
		return Objects.equals(labels, that.labels) &&
				Objects.equals(series, that.series) &&
				Objects.equals(data, that.data);
	}

	@Override public int hashCode() {
		return Objects.hash(labels, series, data);
	}

	@Override public String toString() {
		return "TrashEntry{" +
				"labels=" + labels +
				", series=" + series +
				", data=" + data +
				'}';
	}
}
