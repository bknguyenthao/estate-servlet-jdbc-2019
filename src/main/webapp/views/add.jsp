<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm tòa nhà</title>
</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a
						href="<c:url value="/admin-home"/>">Trang chủ</a></li>
					<li><a href="<c:url value="/admin-building?action=LIST"/>">Danh
							sách sản phẩm</a></li>
					<li><a href="#">Thêm sản phẩm</a></li>
				</ul>
				<!-- /.breadcrumb -->
			</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<div class="widget-body">
							<div class="widget-main">
								<div class="form-horizontal">
									<form id="formAdd">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="form-field-1-1"><b> Tên sản phẩm </b></label>
											<div class="col-sm-10">
												<input type="text" id="form-field-1-1" class="form-control"
													name="name">
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="form-field-1-1"><b> Diện tích sàn </b></label>
											<div class="col-sm-10">
												<input type="text" id="form-field-1-1" class="form-control"
													name=buildingArea>
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="form-field-1-1"><b> Quận </b></label>
											<div class="col-sm-3">
												<select class="form-control" name="district">
													<option value="">-- Chọn quận --</option>
													<c:forEach var="item" items="${districts}">
														<option value="${item.key}">${item.value}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="form-field-1-1"><b> Phường </b></label>
											<div class="col-sm-10">
												<input type="text" id="form-field-1-1" class="form-control"
													name="ward">
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="form-field-1-1"><b> Đường </b></label>
											<div class="col-sm-10">
												<input type="text" id="form-field-1-1" class="form-control"
													name="street">
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="form-field-1-1"><b> Hướng </b></label>
											<div class="col-sm-10">
												<input type="text" id="form-field-1-1" class="form-control"
													name="direction">
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="form-field-1-1"><b> Hạng </b></label>
											<div class="col-sm-10">
												<input type="text" id="form-field-1-1" class="form-control"
													name="level">
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="form-field-1-1"><b> Số tầng hầm </b></label>
											<div class="col-sm-10">
												<input type="text" id="form-field-1-1" class="form-control"
													name="numberOfBasement">
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="form-field-1-1"><b> Diện tích thuê </b></label>
											<div class="col-sm-10">
												<input type="text" id="form-field-1-1" class="form-control"
													name="rentArea">
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="form-field-1-1"><b> Giá thuê </b></label>
											<div class="col-sm-10">
												<input type="text" id="form-field-1-1" class="form-control"
													name="costRent">
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="form-field-1-1"><b> Tên quản lý </b></label>
											<div class="col-sm-10">
												<input type="text" id="form-field-1-1" class="form-control"
													name="managerName">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="form-field-1-1"><b> Số điện thoại quản lý </b></label>
											<div class="col-sm-10">
												<input type="text" id="form-field-1-1" class="form-control"
													name="managerPhone">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="form-field-1-1"><b> Loại tòa nhà </b></label>
											<div class="col-xs-12 col-sm-5">
												<div class="control-group">
													<div class="fg-line">
														<c:forEach var="item" items="${buildingtypes}">
															<label class="checkbox-inline"> <input
																type="checkbox" value="${item.key}" name="buildingType">
																${item.value}
															</label>
														</c:forEach>
													</div>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="form-field-1-1"><b> Upload hình ảnh </b></label>
											<div class="col-xs-10">
												<label class="ace-file-input"><input type="file"
													id="id-input-file-2"><span
													class="ace-file-container" data-title="Choose"><span
														class="ace-file-name" data-title="No File ..."><i
															class=" ace-icon fa fa-upload"></i></span></span><a class="remove"
													href="#"><i class=" ace-icon fa fa-times"></i></a></label>
											</div>
										</div>
									</form>
									<div class="form-group">
										<div class="col-md-offset-2 col-md-10">
											<button class="btn btn-info" type="button" id="btnAdd">
												<i class="ace-icon fa fa-check bigger-110"></i> Thêm
											</button>
											&nbsp; &nbsp; &nbsp;
											<button class="btn" type="reset">
												<i class="ace-icon fa fa-undo bigger-110"></i> Hủy
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /.main-content -->
	<script type="text/javascript">
		$("#btnAdd").click(function() {
			addBuilding();
		});
		function addBuilding() {
			var formData = $("#formAdd").serializeArray();
			var data = {};
			var buildingTypes = [];
			$.each(formData, function(index, v) {
				if (v.name == 'buildingType') {
					buildingTypes.push(v.value);
				} else {
					data["" + v.name + ""] = v.value;
				}
			});
			data["buildingTypes"] = buildingTypes;
			sendAjaxAdd(data);
		}

		function sendAjaxAdd(data) {
			$
					.ajax({
						type : "POST",
						url : "<c:url value = '/api-admin-building'/>",
						data : JSON.stringify(data),
						contentType : "application/json",
						dataType : "json",
						crossDomain : true,
						success : function(msg) {
							window.location.href = "<c:url value = '/admin-building?action=ADD'/>";
							alert("success");

						},
						error : function(request, status, error) {
							window.location.href = "<c:url value = '/admin-building?action=ADD'/>";
							alert(error);
						}
					});
		}
	</script>
</body>
</html>